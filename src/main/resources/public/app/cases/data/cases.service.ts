import { Case } from './case';
import { CASES } from './mock_cases';
import { Injectable } from '@angular/core';

@Injectable()
export class CasesService {

    private id: number = 1;

    getCases(): Promise<Case[]> {
        return Promise.resolve(CASES);
    }

    getCase(id:number): Case {
        return CASES.find(item => item.id === id);
    }

    addCase(type: string, customer: string) {
        CASES.push({
            type: type,
            id: this.id,
            customer: customer
        });
        this.id++;
    }

    loadCases() {
        CASES.forEach((value, index) => value.id = index + 1);
        this.id = CASES.length + 1;
    }
}
